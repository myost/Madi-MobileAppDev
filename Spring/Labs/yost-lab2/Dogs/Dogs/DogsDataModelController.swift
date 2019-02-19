//
//  BreedsDataModelController.swift
//  Dogs
//
//  Created by Madi Yost on 2/18/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import Foundation

class DogsDataModelController {
    var allData = [BreedsDataModel]()
    let fileName = "dogBreeds"
    
    func loadData(){
        if let pathURL = Bundle.main.url(forResource: fileName, withExtension: "plist"){
            //create property list decoder object
            let plistdecoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathURL)
                //decode the plist
                allData = try plistdecoder.decode([BreedsDataModel].self, from: data)
            }catch{
                //handle error from try statements here
                print(error)
            }
        }
    }
    
    func getGroups() -> [String]{
        var groups = [String]()
        for item in allData{
            groups.append(item.group)
        }
        return groups
    }
    
    func getBreeds(index: Int) -> [String] {
        return allData[index].breeds
    }
    
    func addBreed(index: Int, newBreed: String, newIndex: Int){
        allData[index].breeds.insert(newBreed, at: newIndex)
    }
    
    func deleteBreed(index: Int, breedIndex: Int){
        allData[index].breeds.remove(at: breedIndex)
    }
    
}
