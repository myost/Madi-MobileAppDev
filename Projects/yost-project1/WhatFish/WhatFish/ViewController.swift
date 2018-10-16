//
//  ViewController.swift
//  WhatFish
//
//  Created by Madi Yost on 10/11/18.
//  Copyright © 2018 Madi Yost. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var numFishSwitch: UISwitch!
    @IBOutlet weak var switchLabel: UILabel!
    @IBOutlet weak var tankSize: UITextField!
    @IBOutlet weak var maintenenceControl: UISegmentedControl!
    @IBOutlet weak var fishImage: UIImageView!
    @IBOutlet weak var fishSuggestion: UILabel!
    @IBOutlet weak var backgroundView: UIView!
    @IBOutlet weak var moreButton: UIButton!
    @IBOutlet weak var maintenanceView: UIStackView!
    
    var numFish : Int?
    
    @IBAction func onButtonPressed(_ sender: Any) { //found after extensive investigation into WKWebView and a failed attempt to use a Web View that this will open a link in a new webpage, code modified from apple documentation and video tutorial https://www.youtube.com/watch?v=Epb_ZZBFZIs
        UIApplication.shared.open(URL(string: "https://www.petsmart.com/fish/live-fish/goldfish-betta-and-more/")! as URL, options: [:], completionHandler: nil)
    }
    
    @IBAction func handleTap(_ sender: UITapGestureRecognizer) {
        if sender.state == .ended {
            tankSize.resignFirstResponder()
        }
    }
    func updateLabel(){
        if numFishSwitch.isOn{
            switchLabel.text = "multiple fish"
        }
        else {
            switchLabel.text = "one fish"
        }
    }
    
    func changeImage(fileName name : String, imageCaption caption : String){
        fishImage.image = UIImage(named: name)
        fishSuggestion.text = caption
    }
    
    func updateFishSuggestion(){
        let fishTankSize = Int(tankSize.text!) //to hold the tank size
        var myFishTankSize : CGFloat
        if fishTankSize != nil {
            if fishTankSize! > 0 && fishTankSize! < 21{
                myFishTankSize = CGFloat(fishTankSize!)
                backgroundView.backgroundColor = UIColor(red: 0.5529, green: 0.8823, blue: 1, alpha: (myFishTankSize/20))
            }else {
                //create a UIAlertController object - modified from notes
                let alert=UIAlertController(title: "Warning", message: "The tank size must be between 1 and 20 gallons", preferredStyle: UIAlertControllerStyle.alert)
                //create a UIAlertAction object for the button
                let cancelAction=UIAlertAction(title: "Cancel", style:UIAlertActionStyle.cancel, handler: nil)
                alert.addAction(cancelAction) //adds the alert action to the alert object
                let okAction=UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in
                    self.tankSize.text="1"
                    self.updateFishSuggestion()
                })
                alert.addAction(okAction)
                present(alert, animated: true, completion: nil)
            }
        
            if numFishSwitch.isOn{ //choose between the guppy and the goldfish
                maintenanceView.isHidden = false
                if maintenenceControl.selectedSegmentIndex == 0{
                    //the low maintenence segment is selected
                    changeImage(fileName : "guppy.png", imageCaption: "We recommend a Guppy fish!")
                    moreButton.isHidden = false
                }
                else if maintenenceControl.selectedSegmentIndex == 1{
                    changeImage(fileName : "goldfish.png", imageCaption: "We recommend a Goldfish!")
                    moreButton.isHidden = false
                }else{
                    changeImage(fileName : "unknown.png", imageCaption: "")
                    moreButton.isHidden = true
                }
            }else{//we want to suggest a betta fish regardless of other inputs
                changeImage(fileName : "beta.png", imageCaption: "We recommend a Betta fish!")
                moreButton.isHidden = false
                maintenanceView.isHidden = true
            }
            
        }else{
        moreButton.isHidden = true
        }
        
    }
    
    @IBAction func changeNumFish(_ sender: UISwitch) {
        updateLabel()
        updateFishSuggestion()
    }
    
    @IBAction func changeMaintenence(_ sender: UISegmentedControl) {
        
        updateFishSuggestion()
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        updateFishSuggestion()
    }
    
    @objc func keyboardWillChange(notification: Notification){ //code to move screen when the keyboard pops up modified from video tutorial by Paul Solt at https://www.youtube.com/watch?v=iUQ1GfiVzS0 and https://www.youtube.com/watch?v=xVZubAMFuIU
        if UIDevice.current.orientation.isLandscape && UIDevice.current.userInterfaceIdiom == .phone { //device orientation and userInterfaceIdiom found on apple documentation under UIDevice fter searching orientation and findign userInterfaceIdiom as a result for finding device type in google search
            guard let keyboardRect = (notification.userInfo?[UIKeyboardFrameEndUserInfoKey] as? NSValue)?.cgRectValue else{
                return
            }
            if notification.name == Notification.Name.UIKeyboardWillShow || notification.name == Notification.Name.UIKeyboardWillChangeFrame{
                view.frame.origin.y = -(keyboardRect.height)/2
            }else{
                view.frame.origin.y = 0
            }
        }
    }
    
    
    override func viewDidLoad() {
        
        tankSize.delegate = self
        moreButton.isHidden = true
        maintenanceView.isHidden = true
        //listening for keyboard events - also from Paul Solt tutorial - like adding event listeners to webpage in JS
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillChange(notification:)), name: NSNotification.Name.UIKeyboardWillShow, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillChange(notification:)), name: NSNotification.Name.UIKeyboardWillHide, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillChange(notification:)), name: NSNotification.Name.UIKeyboardWillChangeFrame, object: nil)
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    deinit { //need to make sure to do this to not mess things up - also from Paul Solt tutorial
        NotificationCenter.default.removeObserver(self, name: NSNotification.Name.UIKeyboardWillShow, object: nil)
        NotificationCenter.default.removeObserver(self, name: NSNotification.Name.UIKeyboardWillHide, object: nil)
        NotificationCenter.default.removeObserver(self, name: NSNotification.Name.UIKeyboardWillChangeFrame, object: nil)

    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

