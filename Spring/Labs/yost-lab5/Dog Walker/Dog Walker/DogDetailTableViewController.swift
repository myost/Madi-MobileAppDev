//
//  DogDetailTableViewController.swift
//  Dog Walker
//
//  Created by Madi Yost on 3/13/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit

class DogDetailTableViewController: UITableViewController {
    var dogName = String()
    var dogBreed = String()
    var dogSize = String()
    var dogOwner = String()
    
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var breedLabel: UILabel!
    @IBOutlet weak var sizeLabel: UILabel!
    @IBOutlet weak var ownerLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationItem.largeTitleDisplayMode = .never
    }
    
    override func viewWillAppear(_ animated: Bool) {
        nameLabel.text = dogName
        breedLabel.text = dogBreed
        sizeLabel.text = dogSize
        ownerLabel.text = dogOwner
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
