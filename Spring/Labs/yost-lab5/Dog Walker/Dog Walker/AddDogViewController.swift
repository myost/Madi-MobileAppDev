//
//  AddDogViewController.swift
//  Dog Walker
//
//  Created by Madi Yost on 3/13/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit

class AddDogViewController: UIViewController {
    var addedName = String()
    var addedBreed = String()
    var addedOwner = String()
    var addedSize = "Small"
    
    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var breedTextField: UITextField!
    @IBOutlet weak var ownerTextField: UITextField!
    @IBOutlet weak var sizeSegmentedContrl: UISegmentedControl!
    
    @IBAction func sizeSelected(_ sender: Any) {
        //set the size to the selected size
        addedSize = sizeSegmentedContrl.titleForSegment(at: sizeSegmentedContrl.selectedSegmentIndex)!
    }
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    


    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
        if segue.identifier == "donesegue"{
            if nameTextField.text?.isEmpty == false,
                breedTextField.text?.isEmpty == false,
                ownerTextField.text?.isEmpty == false {
                addedName = nameTextField.text!
                addedBreed = breedTextField.text!
                addedOwner = ownerTextField.text!
            }
        }
    }


}
