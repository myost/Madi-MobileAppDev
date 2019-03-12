//
//  FishDetailViewController.swift
//  Fish Food
//
//  Created by Madi Yost on 3/12/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit

class FishDetailViewController: UIViewController {
    var fishData = FishTankDataController()
    var selectedFish = 0
    var fishList = [Fish]()
    var fishToDisplay = Fish()
    
    @IBOutlet weak var fishImage: UIImageView!
    @IBOutlet weak var fishNameLabel: UILabel!
    @IBOutlet weak var fishSpeciesLabel: UILabel!
    @IBOutlet weak var fishAgeLabel: UILabel!
    @IBOutlet weak var fishTankLabel: UILabel!
    @IBOutlet weak var fishDescriptionLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        fishList = fishData.getFish()
        fishToDisplay = fishList[selectedFish]
        fishNameLabel.text = fishToDisplay.name
        fishTankLabel.text = String(fishToDisplay.tankSize)+" gallons"
        fishDescriptionLabel.text = fishToDisplay.details
        fishSpeciesLabel.text = fishToDisplay.species
        fishImage.image = UIImage(named: fishToDisplay.image)
        let age = Date(timeIntervalSinceNow: 0).timeIntervalSince(fishToDisplay.age)
        let years = Int(age) / 31536000
        let months = Int(age) / 2592000 % 12
        let days = Int(age) / 86400 % 30
        var ageString = String()
        if years == 1{
            ageString = "\(years) year"
        }else if years > 1{
            ageString = "\(years) years"
        }
        if months == 1 {
            ageString += " \(months) month"
        }else if months > 1 {
            ageString += " \(months) months"
        }
        if days == 1{
            ageString += " \(days) day"
        }else if days > 1{
            ageString += " \(days) days"
        }
        if years == 0 && months == 0 && days == 0 {
            ageString = "less than a day"
        }
        ageString += " old"
        fishAgeLabel.text = ageString
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
