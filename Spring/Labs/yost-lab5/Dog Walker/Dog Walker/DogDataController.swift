//
//  DogDataController.swift
//  Dog Walker
//
//  Created by Madi Yost on 3/13/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import Foundation
import Firebase

class DogDataController {
    var ref: DatabaseReference!
    var dogData = [Dog]()
    //property with a closure as its value
    //closure takes an array of Recipe as its parameter and Void as its return type
    var onDataUpdate: ((_ data: [Dog]) -> Void)?
    
    func setupFirebaseListener(){
        ref = Database.database().reference().child("dogs")
        //set up a listener for Firebase data change events
        //this event will fire with teh inital data and then all the changes
        ref.observe(DataEventType.value, with: {snapshot in self.dogData.removeAll()
            //data snapshot represents the Firebase data at a given time
            //loop through all the child data nodes
            for snap in snapshot.children.allObjects as! [DataSnapshot]{
                print(snap)
                let dogID = snap.key
                if let dogDict = snap.value as? [String: String], //get value as a dictionary
                    let dogName = dogDict["name"],
                    let dogBreed = dogDict["breed"],
                    let dogSize = dogDict["size"],
                    let dogOwner = dogDict["owner"]
                {
                    let newDog = Dog(id: dogID, name: dogName, breed: dogBreed, size: dogSize, owner: dogOwner)
                    self.dogData.append(newDog)
                }
            }
            //passing the results to the onDataUpdate
            self.onDataUpdate?(self.dogData)
        })
    }
    
    func getDogs()->[Dog]{
        return dogData
    }
    
    func addDog(name: String, breed: String, size: String, owner: String){
        //create Dictionary
        let newDogDict = ["name": name, "breed":breed, "size":size, "owner":owner]
        //create a new ID
        let dogRef = ref.childByAutoId()
        
        //write data to the new ID in Firebase
        dogRef.setValue(newDogDict)
    }
    
    func deleteDog(dogID: String){
        //delete the item from Firebase
        ref.child(dogID).removeValue()
    }
}
