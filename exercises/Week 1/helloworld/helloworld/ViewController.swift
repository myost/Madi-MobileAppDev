//
//  ViewController.swift
//  helloworld
//
//  Created by Madi Yost on 8/30/18.
//  Copyright © 2018 Madi Yost. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var messageText: UILabel!
    @IBAction func buttonPress(_ sender: UIButton) {
        messageText.text="Hello World"
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

