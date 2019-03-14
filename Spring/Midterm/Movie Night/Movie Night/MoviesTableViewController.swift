//
//  MoviesTableViewController.swift
//  Movie Night
//
//  Created by Madi Yost on 3/14/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit

class MoviesTableViewController: UITableViewController {
    var movieData = MovieDataController()
    var movieList = [String]()
    
    func render(){
        movieList = movieData.getMovies()
        //reload the table data
        tableView.reloadData()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        movieData.loadData()
        movieList = movieData.getMovies()
        navigationController?.navigationBar.prefersLargeTitles = true
        //application instance
        let app = UIApplication.shared
        //subscribe to the UIApplicationWillResignAciveNotification
        NotificationCenter.default.addObserver(self, selector: #selector(MoviesTableViewController.applicationWillResignActive(_:)), name: UIApplication.willResignActiveNotification, object: app)
    }
    
    @objc func applicationWillResignActive(_ notification: NSNotification){
        movieData.writeData()
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        //return the number of rows
        return movieList.count
    }


    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "moviecell", for: indexPath)
        cell.textLabel?.text = movieList[indexPath.row]
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
            //remove from data model instance
            movieData.deleteMovie(movieIndex: indexPath.row)
            //remove from the list
            movieList.remove(at: indexPath.row)
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
            
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }


    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
        if segue.identifier == "moviedetail"{
            let detailVC = segue.destination as! DetailViewController
            if let indexPath = self.tableView.indexPathForSelectedRow {
                let url = movieData.getUrl(index: indexPath.row)
                detailVC.movieURL = url
                detailVC.title = movieList[indexPath.row]
            }
        }
    }

    @IBAction func unwindSegue(segue: UIStoryboardSegue){
        if segue.identifier == "donesegue"{
            let source = segue.source as! ViewController
            if source.addedName.isEmpty == false {
                //add the movie to the data source
                movieData.addMovie(movieName: source.addedName, movieUrl: source.addedURL)
                self.render()
            }
        }
    }

}
