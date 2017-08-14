# SarCar

SARCAR is a Collision Avoidance System which based on Cognitive Radio(smart broadcasting) and GPRS Technology.Cognitive radio allows a cellular network to dynamically lease the under-utilised frequency bands without causing harmful interference to the incumbents.

Presently Collision Avoidance Systems are built on hardware and are very expensive and are only used in expensive cars.

Smart Cities,Smart phones, Let's give it in for Smart Vehicles this time. Accidents happen at intersections, accidents happen due to over-speeding of vehicles,Ambulances get stuck in traffic jams,etc. Sarcar provides a medium for Vehicles to interact smartly. Vehicles connected by a link similar to cognitive link using 5G are able to share their location and speed parameters using the sensors inbuilt in the Android phone (or Android Auto). We have made(upgrading) a predictive algorithm to tell whether an accident will happen between two vehicles or not. Taking the parameters, in case of accidents our machine will learn under what circumstances of parameters do accidents happen and will get better every time to predict accidents. We will also analyse the data of streets and intersections where frequency of accidents is higher and the concerned authorities can take action to prevent this. In cases of accidents we have sufficient data to find out the cause of the accident which will also help in further investigation.

Our prototype has an Android App which currently runs on Android phone but will in future run in Android for Cars. This application is the primary source of data collection for our algorithm.The Android App collects data and sends it to the firebase database where our algorithm coded in python predicts whether an accident is possible or not.

Sarcar is a startup which is working with Indian government agencies to study the scope of GPS for collision avoidance. We are actively researching on the use of Cognitive Radio and have implemented a cognitive radio like mechanism using GPS and 4G Technology. Other use cases of our app is to alert in case of emergency nearby so that all vehichles can give way to the emergency vehichle(eg Ambulance). It often is the case when Ambulances get stuck in traffic and many patients die due to this problem. Our governement recognizes this problem and has tried ways to solve this by Informing people about ambulances going by FM but this does not target specific people who actually will be affected by the ambulance but targets the complete city. Our Algorithm of finding nearby vehichles using haversine formula helps to solve this problem too.

Auto Traffic Penalties / E-challan : We can easily get hold of of traffic defaulters who overspeed. We will be able to penalize drivers who overspeed without the need of traffic police. A driver will get notification warning 5 times before he is penalized.

Realtime Traffic-Police Supervision And specific Broadcast :

It allows traffic police to interact with a particular section of vehicles.

Eg : In case of a broken bridge , Traffic police supervisor can directly alert a group of people travelling through that route to switch their route .

We have Graphically represented Accident data in the United Kingdoms as shown in the presentation using Accidents dataset of UK provided by Kaggle which clearly shows that UK needs something to prevent road accidents and i believe our solution can make wonders.

We are predicting Accidents by Artificial Neural Network as shown in the Sarcar.ipynb file with all detail information.(https://github.com/PiyushGupta007/SarCar/blob/master/ML%20AND%20PYTHON/SarCar-Collision-Avoidance.ipynb)
