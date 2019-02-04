//
//  WorldDestinationsController.swift
//  lab1_tabs
//
//  Created by Madi Yost on 2/1/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import Foundation

class WorldDestinationsController {
    var allData = [WorldDestinations]()
    let fileName = "worldDestinations"
    
    func loadData(){
        if let pathURL = Bundle.main.url(forResource: fileName, withExtension: "plist"){
            //only enter this statement if the pathURL exists, otherwise we won't get here
            //create property list decoder object
            let plistdecoder = PropertyListDecoder()
            do{
                let data = try Data(contentsOf: pathURL) //this function throws an error if it fails - so we will have a catch to handle it
                //if we could pull the data then decode it
                allData = try plistdecoder.decode([WorldDestinations].self, from: data)
            }catch{
                //handle the error
                print(error)
            }
        }
    }
    
    //getter method to get all of the continent names
    func getContinents() -> [String]{
        var destinations = [String]()
        for continent in allData {
            destinations.append(continent.continent)
        }
        return destinations
    }
    
    //getter method to get all of the cities for a continent
    func getCities(index: Int) -> [String]{
        return allData[index].cities
    }
}
