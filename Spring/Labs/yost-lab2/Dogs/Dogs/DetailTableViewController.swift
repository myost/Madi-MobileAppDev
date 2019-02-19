//
//  DetailTableViewController.swift
//  Dogs
//
//  Created by Madi Yost on 2/18/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit

class DetailTableViewController: UITableViewController {
    var groupsData = DogsDataModelController()
    var selectedGroup = 0
    var breedList = [String]()
    var searchController = UISearchController()

    override func viewDidLoad() {
        super.viewDidLoad()
        //searchController.navigationItem.largeTitleDisplayMode = .never
        tableView.tableHeaderView = searchController.searchBar
        //make the title for this view smaller
        self.navigationItem.largeTitleDisplayMode = .never
        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
//         self.navigationItem.rightBarButtonItem = self.editButtonItem
    }
    
    override func viewWillAppear(_ animated: Bool) {
        breedList = groupsData.getBreeds(index: selectedGroup)
        let resultsController = SearchResultsController() //create an instance of the searchresultscontroller class
        resultsController.allBreeds = breedList //set the all breeds property to our array of breeds
        searchController = UISearchController(searchResultsController: resultsController) //initialize the search controller with the results controller when it has search results to display
        //search bar configuration
        searchController.searchBar.placeholder = "Enter a search term"
        searchController.searchBar.sizeToFit()
        searchController.navigationItem.largeTitleDisplayMode = .never
        tableView.tableHeaderView = searchController.searchBar
        searchController.searchResultsUpdater = resultsController //sets instance to update seach results

    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return breedList.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "GroupIdentifier", for: indexPath)

        // Configure the cell...
        cell.textLabel?.text = breedList[indexPath.row]
        
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
            //delete breed from the array
            breedList.remove(at: indexPath.row)
            //delete the breed from the data model instance
            groupsData.deleteBreed(index: selectedGroup, breedIndex: indexPath.row)
            // Delete the row from the data source (aka table)
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }

    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {
        let fromRow = fromIndexPath.row //row being moved from
        let toRow = to.row //row being moved to
        let moveBreed = breedList[fromRow]
        //swap positions in the array
        breedList.swapAt(fromRow, toRow)
        //move the breed in the data model instance as well
        //delete first
        groupsData.deleteBreed(index: selectedGroup, breedIndex: fromRow)
        //then insert
        groupsData.addBreed(index: selectedGroup, newBreed: moveBreed, newIndex: toRow)
    }

    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */
    
    @IBAction func unwindSegue (_ segue: UIStoryboardSegue){
        if segue.identifier == "donesegue"{
            let source = segue.source as! AddBreedViewController
            //only add a breed if there is text in the textfield
            if source.addedBreed.isEmpty == false {
                //add breed to our data model instance
                groupsData.addBreed(index: selectedGroup, newBreed: source.addedBreed, newIndex: breedList.count)
                //add breed to the array
                breedList.append(source.addedBreed)
                //reload the table so that we see the new breed the user just added
                tableView.reloadData()
            }
        }
    }

}
