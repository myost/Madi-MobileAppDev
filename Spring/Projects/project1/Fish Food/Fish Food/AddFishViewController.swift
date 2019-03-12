//
//  AddFishViewController.swift
//  Fish Food
//
//  Created by Madi Yost on 3/7/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit

class AddFishViewController: UIViewController {
    var fishName = String()
    var fishSpecies = String()
    var dateBought = Date()
    var fishImage = String()
    var fishDetails = String()
    var tankSize = Int()
    
    var betaDescr = "Beta fish need to be fed twice a day, once in the morning and once at night. Beta fish should never be in a tank with other beta fish but get along well with other fish species."
    var goldDescr = "Goldfish generally need to be fed twice a day but they can survive up to 2 weeks without feeding. An average goldfish will live 10 years or longer. Goldfish are a great fish for beginners but need a large tank to be comfortable."
    var guppyDescr = "Guppies generally swim towards the top or middle of the tank. They should generally be fed twice a day. It is recommended to have a tank with plants if you have a guppy. Guppies get along well with each other and with other fish species."
    
    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var speciesSegmentedControl: UISegmentedControl!
    @IBOutlet weak var boughtDatePicker: UIDatePicker!
    
    //action functions to dismiss the keyboard
    @IBAction func textFieldDoneEditing(_ sender: UITextField) {
        sender.resignFirstResponder()
    }
    
    @IBAction func onTapGestureRecognized(_ sender: Any) {
        nameTextField.resignFirstResponder()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        dateBought = boughtDatePicker.date
        fishSpecies = "Beta"
        fishImage = "beta.png"
        fishDetails = betaDescr
        tankSize = 2
        // Do any additional setup after loading the view.
    }
    
    //function that updates the species when the user selects a species
    @IBAction func speciesSelected(_ sender: UISegmentedControl) {
        if speciesSegmentedControl.selectedSegmentIndex == 0{
            //user has selected beta fish
            fishSpecies = "Beta"
            fishImage = "beta.png"
            fishDetails = betaDescr
            tankSize = 2
            
        }
        else if speciesSegmentedControl.selectedSegmentIndex == 1{
            //user has selected goldfish
            fishSpecies = "Goldfish"
            fishImage = "goldfish.png"
            fishDetails = goldDescr
            tankSize = 29
        }
        else if speciesSegmentedControl.selectedSegmentIndex == 2{
            //user has selected guppy
            fishSpecies = "Guppy"
            fishImage = "guppy.png"
            fishDetails = guppyDescr
            tankSize = 5
        }
    }
    
    //function that updates the bought date when the user selects a date
    @IBAction func setBoughtDate(_ sender: UIDatePicker) {
        dateBought = boughtDatePicker.date
    }
    


    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
        if segue.identifier == "doneSegue"{
            if nameTextField.text?.isEmpty == false{
                fishName = nameTextField.text!
            }
        }
    }

}
