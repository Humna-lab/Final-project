from flask import Flask, jsonify
from flask import request


import numpy as np


import sys
import os
import cv2
import tensorflow as tf
# Disable tensorflow compilation warnings
os.environ['TF_CPP_MIN_LOG_LEVEL']='2'
import tensorflow as tf

app = Flask(__name__)



@app.route('/', methods=['POST'])
def get_task():


    datas = bytes(request.data)
    fh = open("imageToSave619.jpg", "wb")
    fh.write(datas)
    fh.close()

    img=cv2.imread("imageToSave619.jpg")
    img=cv2.resize(img,(500,500))
    img=np.rot90(img)
    img = np.rot90(img)
    img = np.rot90(img)
    # (h, w) = img.shape[:2]
    # # calculate the center of the image
    # center = (w / 2, h / 2)
    # M = cv2.getRotationMatrix2D(center, 90, 1.0)
    # rotated90 = cv2.warpAffine(img, M, (h, w))
    cv2.imwrite("imageToSave619.jpg",img)
    cv2.imshow("this",img)
    cv2.waitKey(1000)

    image_path = "imageToSave619.jpg"
    # Read the image_data
    image_data = tf.gfile.FastGFile(image_path, 'rb').read()

    # print(image_data)
    #
    # datas = bytes(image_data)
    # fh = open("imageToSave1234.jpg", "wb")
    # fh.write(datas)
    # fh.close()

    # Loads label file, strips off carriage return*
    label_lines = [line.rstrip() for line
                   in tf.gfile.GFile("asl_labels.txt")]

    # Unpersists graph from file
    with tf.gfile.FastGFile("asl_graph.pb", 'rb') as f:
        graph_def = tf.GraphDef()
        graph_def.ParseFromString(f.read())
        _ = tf.import_graph_def(graph_def, name='')
    #    print("lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll",_)

    with tf.Session() as sess:
        # Feed the image_data as input to the graph and get first prediction
        softmax_tensor = sess.graph.get_tensor_by_name('final_result:0')
        # tensor_name_list = [tensor.name for tensor in tf.get_default_graph().as_graph_def().node]
        # for tensor_name in tensor_name_list:
        #     print(tensor_name, '\n')
        predictions = sess.run(softmax_tensor, {'DecodeJpeg/contents:0': image_data})

        # Sort to show labels of first prediction in order of confidence

        top_k = predictions[0].argsort()[-len(predictions[0]):][::-1]

        for node_id in top_k:
            human_string = label_lines[node_id]
            score = predictions[0][node_id]
            print('%s (score = %.5f)' % (human_string, score))

        print(predictions[0][top_k[0]])
        print(label_lines[top_k[0]])
    return (str(label_lines[top_k[0]]));

if __name__ == '__main__':
    app.run(host='0.0.0.0',debug=True,port=8000,threaded=True)
