//
//  CharacterDataModelController.swift
//  LOTR
//
//  Created by Madi Yost on 2/24/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import Foundation

class CharacterDataModelController{
    var allData = [Character]()
    let fileName = "lordOfTheRings"
    
    func loadData(){
        if let pathURL = Bundle.main.url(forResource: fileName, withExtension: "plist"){
            //create a plist decoder object
            let plistdecoder = PropertyListDecoder()
            do{
                let data = try Data(contentsOf: pathURL)
                //decodes the plist
                allData = try plistdecoder.decode([Character].self, from: data)
            }catch{
                //handle error
                print(error)
            }
        }
    }
    
    func getCharacters() -> [String]{
        var characters = [String]()
        for character in allData{
            characters.append(character.name)
        }
        return characters
    }
    
    func getImage(index:Int) -> String{
        return allData[index].image
    }
    
    func getDescr(index: Int) -> String{
        return allData[index].description
    }
    
    func getStatus(index: Int) -> String{
        return allData[index].status
    }
    
    func getRace(index: Int) -> String{
        return allData[index].race
    }
}
