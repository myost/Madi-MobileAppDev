//
//  Add BreedViewController.swift
//  Dogs
//
//  Created by Madi Yost on 2/18/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit

class AddBreedViewController: UIViewController {
    var addedBreed = String()
    
    @IBOutlet weak var breedTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
        if segue.identifier == "donesegue" {
            //only add a breed if there is text in the textfield (no empty breeds)
            if breedTextField.text?.isEmpty == false {
                addedBreed = breedTextField.text!
            }
        }
    }
    @IBAction func onTapGestureREcognized(_ sender: Any) {
        breedTextField.resignFirstResponder()
    }
    
    @IBAction func textFieldDoneEditing(_ sender: UITextField) {
        sender.resignFirstResponder()
    }

}
