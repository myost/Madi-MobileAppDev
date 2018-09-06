//
//  ViewController.swift
//  argentina
//
//  Created by Madi Yost on 9/4/18.
//  Copyright © 2018 Madi Yost. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var artImage: UIImageView!
    
    @IBAction func chooseArt(_ sender: UIButton) {
        if sender.tag == 1 {
            artImage.image = UIImage (named: "ar_waterfall")
        }
        else if sender.tag == 2 {
            artImage.image = UIImage (named: "ar_ruins")
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

