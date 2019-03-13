//
//  DogTableViewController.swift
//  Dog Walker
//
//  Created by Madi Yost on 3/13/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit

class DogTableViewController: UITableViewController {
    var dogs = [Dog]()
    var dogData = DogDataController()

    func render(){
        //get dog array
        dogs = dogData.getDogs()
        //reload table view data
        tableView.reloadData()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //assign the closure with the method we want called to the onDataUpdate
        dogData.onDataUpdate = {[weak self] (data:[Dog]) in self?.render()}
        dogData.setupFirebaseListener()
        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        //return the number of rows
        return dogs.count
    }


    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "dogCell", for: indexPath)
        let dog = dogs[indexPath.row]
        cell.textLabel!.text = dog.name
        return cell
    }



    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }



    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            let dogID = dogs[indexPath.row].id
            dogData.deleteDog(dogID: dogID)
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
        if segue.identifier == "dogdetailsegue"{
            let detailVC = segue.destination as! DogDetailTableViewController
            let editingCell = sender as! UITableViewCell
            let indexPath = tableView.indexPath(for: editingCell)
            detailVC.dogName = dogs[indexPath!.row].name
            detailVC.dogBreed = dogs[indexPath!.row].breed
            detailVC.dogSize = dogs[indexPath!.row].size
            detailVC.dogOwner = dogs[indexPath!.row].owner
        }
    }

 
    @IBAction func unwindSegue(segue: UIStoryboardSegue){
        if segue.identifier == "donesegue"{
            let source = segue.source as! AddDogViewController
            if source.addedName.isEmpty == false{
                dogData.addDog(name: source.addedName, breed: source.addedBreed, size: source.addedSize, owner: source.addedOwner)
            }
        }
    }

}
