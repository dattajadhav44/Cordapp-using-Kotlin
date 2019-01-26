# cordapp-using-kotlin
This is a simple corda app consider two BANks does the day to today business and share the fact on need to know basis.
The Banks that i have considered are HSBC and BOA
       HSBC can share the fact with BOA if they wans to OR they can initiate the iou for them selve as well.
       BOA can share the fact with HSBC if they wans to OR they can initiate the iou for them selve as well.
   
clone this repository

1- Clone the repository cordapp-using-kotlin
2- open the project in intellij
3- rebuid the project
4- cd cordapp-using-kotlin
5- ./gradlew clean deployNodes
6- cd build/nodes
7- ./runnodes   - This start the node including the NOTARY
6. go to HSBC or BOA terminal and initate the transaction (eg. below)
    start IOUFlow iouReg: 100, iouName: David, iouEmail: david@gmail.com, iouAdd: US, iouAge:30, otherParty: "O=HSBC,L=Mumbai,C=IN"
    
 7- check the result at respective party
 8- run vaultQuery contractStateType: com.template.IOUState
