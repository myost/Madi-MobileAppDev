//
//  ViewController.swift
//  animals
//
//  Created by Madi Yost on 10/9/18.
//  Copyright © 2018 Madi Yost. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var landAnimalLabel: UILabel!
    @IBOutlet weak var waterAnimalLabel: UILabel!
    
    let filename = "favs.plist"
    var user = Favorite()
    
    @IBAction func unwindSegue(_ segue:UIStoryboardSegue){
        landAnimalLabel.text = user.favLandAnimal
        waterAnimalLabel.text = user.favWaterAnimal
    }
    
    func dataFileURL(_ filename:String) -> URL?{
        //following line returns array of URLs for document directory
        let urls = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        var url : URL?
        //append filename to first item in array (which will be the file directory) - can assume only one will return bc there is always only one documents directory for each app
        url = urls.first?.appendingPathComponent(filename)
        //return the url that we just created (if it doesn't exist we will return nil which is why url is an optional
        return url
    }

    override func viewDidLoad() {
        //get the URL of the data file to write to/read from
        let fileURL = dataFileURL(filename)
        //if the file exists then do stuff
        if FileManager.default.fileExists(atPath: (fileURL?.path)!){
            let url = fileURL! //unwrap because we know that it is okay to do so
            do{
                //create data buffer with contents of plist
                let data = try Data(contentsOf: url)
                //create an instance of the property list decoder
                let decoder = PropertyListDecoder()
                //decode data using structure of Favorite class
                user = try decoder.decode(Favorite.self, from: data)
                //assign data to text fields
                landAnimalLabel.text = user.favLandAnimal
                waterAnimalLabel.text = user.favWaterAnimal
            } catch{
                print("no file")
            }
        }
        else{
            print("file does not exist")
        }
        //create an instance of our app
        let app = UIApplication.shared
        //subscribe to the UIApplicationWillResignActiveNotification
        NotificationCenter.default.addObserver(self, selector: #selector(self.applicationWillResignActive(_:)), name: Notification.Name.UIApplicationWillResignActive, object: app)
        
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    //function called when the UIApplicationWillResignActiveNotification notification is posted
    @objc func applicationWillResignActive(_ notification: Notification){
        //get url of data file
        let fileURL = dataFileURL(filename)
        //create a property list encoder
        let encoder = PropertyListEncoder()
        //set format type to xml - because it's a property list
        encoder.outputFormat = .xml
        do {
            //encode the data using the structure of the Favorite class
            let plistData = try encoder.encode(user)
            //write to file
            try plistData.write(to: fileURL!)
        } catch {
            print("write error ocurred")
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

