//
//  TimerDataController.swift
//  Fish Food
//
//  Created by Madi Yost on 3/10/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import Foundation

class TimerDataModelController {
    var allData = [TimerInstance]()
    let fileName = "defaultTimer"
    let dataFileName = "userTimer.plist"
    
    func getDataFile(datafile: String) -> URL {
        //get path for data file
        let dirPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        let docDir = dirPath[0] //documents directory
        print(docDir)
        
        // URL for our plist
        return docDir.appendingPathComponent(datafile)
    }
    
    func loadData(){
        let pathURL:URL?
        
        // URL for our plist
        let dataFileURL = getDataFile(datafile: dataFileName)
        print(dataFileURL)
        
        //if the data file exists, use it
        if FileManager.default.fileExists(atPath: dataFileURL.path){
            pathURL = dataFileURL
        }
        else {
            // URL for our plist
            pathURL = Bundle.main.url(forResource: fileName, withExtension: "plist")
        }
        if (pathURL != nil) {
            //creates a property list decoder object
            let plistdecoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathURL!)
                //decodes the property list
                allData = try plistdecoder.decode([TimerInstance].self, from: data)
            } catch {
                // handle error
                print(error)
            }
        }
    }
    //all get functions assume that there is only one entry into the plist data (because there is only one timer in the app)
    
    func getLastFed() -> Date {
        return allData[0].lastFed
    }
    
    func getDefaultTime() -> Double {
        return allData[0].time
    }
    
    func setLastFed(lastFedDate: Date){
        allData[0].lastFed = lastFedDate
    }
    
    func writeData(){
        // URL for our plist
        let dataFileURL = getDataFile(datafile: dataFileName)
        print(dataFileURL)
        //creates a property list decoder object
        let plistencoder = PropertyListEncoder()
        plistencoder.outputFormat = .xml
        do {
            let data = try plistencoder.encode(allData.self)
            try data.write(to: dataFileURL)
        } catch {
            // handle error
            print(error)
        }
    }
    
}
