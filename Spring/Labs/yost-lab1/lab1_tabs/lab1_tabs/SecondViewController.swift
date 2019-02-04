//
//  SecondViewController.swift
//  lab1_tabs
//
//  Created by Madi Yost on 2/1/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController {

    @IBAction func goToMaps(_ sender: Any) {
        if (UIApplication.shared.canOpenURL(URL(string: "comgooglemaps://")!)) {
            //if there's an app installed to handle the app scheme then open it
            UIApplication.shared.open(URL(string: "comgooglemaps://")!, options: [:], completionHandler: nil)
        }else{
            if(UIApplication.shared.canOpenURL(URL(string: "maps://")!)){
                UIApplication.shared.open(URL(string: "maps://")!, options: [:], completionHandler: nil)
            }else{
                UIApplication.shared.open(URL(string: "https://www.google.com/maps")!, options: [:], completionHandler: nil)
            }
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }


}

