//
//  DetailViewController.swift
//  Star Wars Films
//
//  Created by Madi Yost on 3/3/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {
    var detailItem: Int?
    var producerItem: String?
    var directorItem: String?
    var crawlItem: String?

    @IBOutlet weak var detailDescriptionLabel: UILabel!
    @IBOutlet weak var directorLabel: UILabel!
    @IBOutlet weak var producerLabel: UILabel!
    @IBOutlet weak var crawlLabel: UILabel!
    
    func configureView() {
        // Update the user interface for the detail labels
        if let episode = detailItem,
            let director = directorItem,
            let producer = producerItem,
            let crawl = crawlItem {
            if let epLabel = detailDescriptionLabel,
                let dirLabel = directorLabel,
                let prodLabel = producerLabel,
                let cLabel = crawlLabel{
                epLabel.text = "Episode "+episode.description
                dirLabel.text = "Directed by: "+director.description
                prodLabel.text = "Produced by: "+producer.description
                cLabel.text = crawl.description
            }
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        configureView()
    }


}

