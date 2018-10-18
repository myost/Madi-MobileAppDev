//
//  ViewController.swift
//  workout-midterm
//
//  Created by Madi Yost on 10/18/18.
//  Copyright © 2018 Madi Yost. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var workoutTime: UITextField!
    @IBOutlet weak var weeklySwitch: UISwitch!
    @IBOutlet weak var switchLabel: UILabel!
    @IBOutlet weak var typeSegCrtl: UISegmentedControl!
    @IBOutlet weak var milesRan: UILabel!
    @IBOutlet weak var caloriesBurned: UILabel!
    @IBOutlet weak var workoutImage: UIImageView!
    
    @IBAction func changedIndex(_ sender: UISegmentedControl) {
        updateTotals()
    }
    
    @IBAction func changedSwitch(_ sender: Any) {
        updateTotals()
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        workoutTime.resignFirstResponder()
        return true
    }
    
    func changeImage(fileName name : String){
        workoutImage.image = UIImage(named: name)
    }

    
    func updateTotals(){
        var time: Float
        var miles: Float
        var calories: Float
        if workoutTime.text!.isEmpty { //text is an optional string, need to force unwrap to check if it is empty
            time = 0.0
        } else {
            time = Float(workoutTime.text!)! //converts the string to a float
        }
        
        //convert time from minutes to hours
        time = time/60
        //calculate the calories burned per day
        if typeSegCrtl.selectedSegmentIndex == 0{//the run case
            changeImage(fileName: "run.png")
            miles = time * 6
            calories = time * 600
        }
        else if typeSegCrtl.selectedSegmentIndex == 1{ //bike case
            changeImage(fileName: "bike.png")
            miles = time * 15
            calories = time * 510
        }
        else if typeSegCrtl.selectedSegmentIndex == 2{ //swim case
            changeImage(fileName: "swim.png")
            miles = time * 2
            calories = time * 420
        }else{ //a catch case to handle errors - should never get here
            miles = 0.0
            calories = 0.0
        }
        if weeklySwitch.isOn{
            miles = miles * 5
            calories = calories * 5
        }
        updateLabels(updateMiles: miles, updateCalories: calories)
    }
    
    func updateLabels(updateMiles: Float, updateCalories: Float){
        let format = NumberFormatter()
        format.numberStyle = NumberFormatter.Style.decimal
        format.minimumFractionDigits = 2
        milesRan.text = format.string(from: NSNumber(value: updateMiles))!+" miles"
        caloriesBurned.text = format.string(from: NSNumber(value: updateCalories))!+" calories burned"
    }
    
    @IBAction func buttonPressed(_ sender: Any) {
        updateTotals()
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        let wTime=Int(workoutTime.text!) //returns an optional & an integer
        if wTime != nil {
            if wTime! < 30{
                let alert=UIAlertController(title: "Workout Alert", message: "You need to exercise more!", preferredStyle: UIAlertControllerStyle.alert) //also could shorten preferred style value to just .alert bc it knows the type
                //create a UIAlertAction object for the button
                let cancelAction=UIAlertAction(title: "Cancel", style:UIAlertActionStyle.cancel, handler: nil)
                alert.addAction(cancelAction) //adds the alert action to the alert object
                let okAction=UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in
                    self.workoutTime.text="30"
                    self.updateTotals() //within this enclosure need to preface everyhting sith self.
                })
                alert.addAction(okAction)
                present(alert, animated: true, completion: nil)
            } //end else
        }
    }
   
    override func viewDidLoad() {
        workoutTime.delegate = self
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

