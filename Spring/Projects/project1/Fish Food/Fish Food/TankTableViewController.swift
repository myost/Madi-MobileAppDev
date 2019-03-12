//
//  TankTableViewController.swift
//  Fish Food
//
//  Created by Madi Yost on 3/6/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit

class TankTableViewController: UITableViewController {
    var fishData = FishTankDataController()
    var fishList = [Fish]()
    //var tank = Tank()
    
    //function to be called whenever our data changes
    func render(){
        fishList = fishData.getFish()
        //tank = fishData.getTank()
        //reload the table view to get the updated data
        tableView.reloadData()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        fishData.onDataUpdate = {[weak self] (data:[Fish]) in self?.render()}
        fishData.dbSetup()
        fishList = fishData.getFish()
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return fishList.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "fishCell", for: indexPath)
        let name = fishList[indexPath.row].name
        cell.textLabel?.text = name
        let imagePath = fishList[indexPath.row].image
        cell.imageView?.image = UIImage(named: imagePath)
        return cell
    }
    
//    override func tableView(_ tableView: UITableView, estimatedHeightForRowAt indexPath: IndexPath) -> CGFloat {
//        return UITableView.automaticDimension
//    }
    
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 110
    }

    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }

    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            //Delete the fish from the realm instance & table
            //first get the fish object to delete
            let fish = fishList[indexPath.row]
            //remove from the realm instance - this will also call render
            fishData.deleteFish(fish: fish)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
        if segue.identifier == "fishDetailSegue"{
            let detailVC = segue.destination as! FishDetailViewController
            let indexPath = tableView.indexPath(for: sender as! UITableViewCell)!
            //sets the data for the destination controller
            detailVC.title = fishList[indexPath.row].name
            detailVC.fishData = fishData
            detailVC.selectedFish = indexPath.row
        }
    }

    @IBAction func unwindsegue(_ segue: UIStoryboardSegue){
        if segue.identifier == "doneSegue"{
            let source = segue.source as! AddFishViewController
            if source.fishName.isEmpty == false {
                let newFish = Fish()
                newFish.name = source.fishName
                newFish.species = source.fishSpecies
                newFish.image = source.fishImage
                newFish.age = source.dateBought
                newFish.tankSize = source.tankSize
                newFish.details = source.fishDetails
                newFish.image = source.fishImage
                fishData.addFish(newFish: newFish)
            }
        }
    }

}
