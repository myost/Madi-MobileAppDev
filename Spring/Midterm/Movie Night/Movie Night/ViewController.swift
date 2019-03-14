//
//  ViewController.swift
//  Movie Night
//
//  Created by Madi Yost on 3/14/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    var addedName = String()
    var addedURL = String()
    
    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var urlTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "donesegue"{
            if nameTextField.text?.isEmpty == false,
                urlTextField.text?.isEmpty == false {
                addedName = nameTextField.text!
                addedURL = urlTextField.text!
            }
        }
    }

}

