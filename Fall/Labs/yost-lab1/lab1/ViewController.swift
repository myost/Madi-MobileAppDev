//
//  ViewController.swift
//  lab1
//
//  Created by Madi Yost on 9/12/18.
//  Copyright © 2018 Madi Yost. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var mountainImage: UIImageView!
    @IBOutlet weak var taglineLabel: UILabel!
    @IBAction func changeImage(_ sender: UIButton) {
        if sender.tag == 1{ //colorado chosen
            mountainImage.image = UIImage (named: "colorado")
        }
        if sender.tag == 2{ //canada chosen
            mountainImage.image = UIImage (named: "canada")
        }
    }

    @IBAction func changeTagline(_ sender: UIButton) {
        if sender.tag == 1{ //colorado chosen
            taglineLabel.attributedText = NSAttributedString (string: "Winter Park, CO")
        }
        if sender.tag == 2{ //canada chosen
            taglineLabel.attributedText = NSAttributedString (string: "Banff, Canada")
        }
        taglineLabel.textAlignment = NSTextAlignment.center
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

