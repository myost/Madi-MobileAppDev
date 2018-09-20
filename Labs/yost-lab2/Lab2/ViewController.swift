//
//  ViewController.swift
//  Lab2
//
//  Created by Madi Yost on 9/19/18.
//  Copyright © 2018 Madi Yost. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var emojiImage: UIImageView!
    @IBOutlet weak var imageCaption: UILabel!
    @IBOutlet weak var textSize: UILabel!
    @IBOutlet weak var switchLabel: UILabel!
    @IBOutlet weak var imageSwitch: UISwitch!
    @IBOutlet weak var textControl: UISegmentedControl!
    
    func updateInfo(){
        if imageSwitch.isOn {
            emojiImage.image = UIImage(named: "smile")
            imageCaption.text = "happy"
        }
        else{
            emojiImage.image = UIImage(named: "frown")
            imageCaption.text = "sad"
        }
    }
    
    func updateFont(){
        if imageSwitch.isOn {
            imageCaption.textColor = UIColor.red
        }
        else{
            imageCaption.textColor = UIColor.blue
        }
        if textControl.selectedSegmentIndex == 0 {
            imageCaption.text = imageCaption.text?.lowercased()
            switchLabel.text = switchLabel.text?.lowercased()
        }
        else if textControl.selectedSegmentIndex == 1 {
            imageCaption.text = imageCaption.text?.capitalized
            switchLabel.text = switchLabel.text?.capitalized
        }
        else{
            imageCaption.text = imageCaption.text?.uppercased()
            switchLabel.text = switchLabel.text?.uppercased()
        }
    }
    
    @IBAction func changeImage(_ sender: UISwitch) {
        updateInfo()
        updateFont()
    }
    @IBAction func changeCaps(_ sender: UISegmentedControl) {
        updateFont()
    }
    @IBAction func changeTextSize(_ sender: UISlider) {
        let fontSize=sender.value
        textSize.text=String(format: "%.0f", fontSize)
        let fontSizeCGFloat=CGFloat(fontSize) //convert float to CGFloat - because it comes in as a float but needs to be a CGFloat to be applied to text
        if let customFont = UIFont(name: "Futura", size: fontSizeCGFloat){
            imageCaption.font = UIFontMetrics.default.scaledFont(for: customFont)
            imageCaption.adjustsFontForContentSizeCategory = true
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

