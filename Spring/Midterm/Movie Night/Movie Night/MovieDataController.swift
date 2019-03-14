//
//  MovieDataController.swift
//  Movie Night
//
//  Created by Madi Yost on 3/14/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import Foundation
import RealmSwift

class MovieDataController {
    let fileName = "movies"
    let persistentDataFileName = "data.plist"
    var allData = [Movie]()
    
    func getDataFile(dataFile: String) -> URL{
        //get path for data file
        let dirPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        let docDir = dirPath[0] ///documents directory
        print(docDir)
        return docDir.appendingPathComponent(dataFile)
    }
    
    func writeData(){
        //URL for our plist
        let dataFileURL = getDataFile(dataFile: persistentDataFileName)
        print(dataFileURL)
        //create a plist encoder object
        let plistencoder = PropertyListEncoder()
        do{
            let data = try plistencoder.encode(allData.self)
            try data.write(to: dataFileURL)
        }catch{
            print(error)
        }
    }
    
    func loadData(){
        let pathURL:URL?
        
        //url for our plist
        let dataFileURL = getDataFile(dataFile: persistentDataFileName)
        print(dataFileURL)
        //if the data file exists use it
        if FileManager.default.fileExists(atPath: dataFileURL.path){
            pathURL = dataFileURL
        }else{
            pathURL = Bundle.main.url(forResource: fileName, withExtension: "plist")
        }
        if pathURL != nil{
            let plistDecoder = PropertyListDecoder()
            do{
                let data = try Data(contentsOf: pathURL!)
                allData = try plistDecoder.decode([Movie].self, from: data)
            }catch{
                //handle errors
                print(error)
            }
        }
    }
    
    func getMovies() -> [String]{
        var movies = [String]()
        for movie in allData{
            movies.append(movie.name)
        }
        return movies
    }
    
    func getUrl(index: Int) -> String {
        return allData[index].url
    }
    
    func addMovie(movieName: String, movieUrl: String){
        let newMovie = Movie(name: movieName, url: movieUrl)
        allData.append(newMovie)
    }
    
    func deleteMovie(movieIndex: Int){
        allData.remove(at: movieIndex)
    }
}
