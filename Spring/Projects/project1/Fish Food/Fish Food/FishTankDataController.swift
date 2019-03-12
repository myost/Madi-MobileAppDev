//
//  FishDataController.swift
//  Fish Food
//
//  Created by Madi Yost on 3/7/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import Foundation
import RealmSwift

class FishTankDataController {
    var fishRealm: Realm! //create a reference to realm
    var fishData: Results<Fish> //initialize a collection of fish objects
    {
        get {
            print(fishRealm.objects(Fish.self))
            return fishRealm.objects(Fish.self) //returns all fish items from the realm database
        }
    }
    
    //property with a closure as its value - for callback
    //closure takes an array of Grocery as its parameter and void as its return type
    var onDataUpdate: ((_ data:[Fish]) -> Void)?
    
    func dbSetup(){
        //initialize the realm database
        do {
            fishRealm = try Realm()
            print(Realm.Configuration.defaultConfiguration.fileURL!)
        } catch let error{
            print(error.localizedDescription)
        }
        onDataUpdate?(Array(fishData)) //converts the collection of objects to an array
        print(Realm.Configuration.defaultConfiguration.fileURL!) //prints the location of realm database
    }
    
    func getFish() -> [Fish]{
        return Array(fishData)
    }
    
    func addFish(newFish: Fish){
        do{
            try self.fishRealm.write({
                self.fishRealm.add(newFish) //add fish to the realm instance
            })
        }catch let error{
            print(error.localizedDescription)
        }
        onDataUpdate?(Array(fishData))
    }
    
    func deleteFish(fish: Fish){
        do{
            try self.fishRealm.write({
                self.fishRealm.delete(fish) //remove fish from the realm instance
            })
        }catch let error{
            print(error.localizedDescription)
        }
        onDataUpdate?(Array(fishData))
    }
    
}
