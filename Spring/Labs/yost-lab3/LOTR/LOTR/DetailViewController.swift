//
//  DetailViewController.swift
//  LOTR
//
//  Created by Madi Yost on 2/24/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {

    @IBOutlet weak var detailDescriptionLabel: UILabel!
    @IBOutlet weak var raceLabel: UILabel!
    @IBOutlet weak var statusLabel: UILabel!
    @IBOutlet weak var characterImage: UIImageView!
    
    var descriptionText: String?
    var raceText: String?
    var statusText: String?
    var imageFileName: String?

    func configureView() {
        // Update the user interface for the description.
        if let detail = descriptionText {
            if let label = detailDescriptionLabel {
                label.text = detail
            }
        }
        //update the UI for the race
        if let race = raceText{
            if let label = raceLabel{
                label.text = race
            }
        }
        //update the UI for the status
        if let status = statusText{
            if let label = statusLabel{
                label.text = status
            }
        }
        //update the UI for the image
        if let imageFile = imageFileName{
            if let imageView = characterImage{
                imageView.image = UIImage(named: imageFile)
            }
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        configureView()
    }


}

