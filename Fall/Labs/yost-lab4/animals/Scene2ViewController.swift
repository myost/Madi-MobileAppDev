//
//  Scene2ViewController.swift
//  animals
//
//  Created by Madi Yost on 10/9/18.
//  Copyright © 2018 Madi Yost. All rights reserved.
//

import UIKit

class Scene2ViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var userLandAnimal: UITextField!
    @IBOutlet weak var userWaterAnimal: UITextField!
    
    @IBAction func handleTap(_ sender: UITapGestureRecognizer) {
        if sender.state == .ended {
            userLandAnimal.resignFirstResponder()
            userWaterAnimal.resignFirstResponder()
        }
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    override func viewDidLoad() {
        userLandAnimal.delegate = self
        userWaterAnimal.delegate = self
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "doneFavs" {
            let scene1ViewController = segue.destination as! ViewController
            //check to see that test was entered into the text fields
            if userLandAnimal.text!.isEmpty == false{
                scene1ViewController.user.favLandAnimal = userLandAnimal.text
            }
            if userWaterAnimal.text!.isEmpty == false {
                scene1ViewController.user.favWaterAnimal = userWaterAnimal.text
            }
        }
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
