# Simple Keras Model  (source: https://deeplearning4j.org/docs/latest/keras-import-overview)

from keras.models import Sequential
from keras.layers import Dense

model = Sequential()
model.add(Dense(units=64, activation='relu', input_dim=100))
model.add(Dense(units=10, activation='softmax'))
model.compile(loss='categorical_crossentropy',optimizer='sgd', metrics=['accuracy'])

model.save('simple_mlp.h5')