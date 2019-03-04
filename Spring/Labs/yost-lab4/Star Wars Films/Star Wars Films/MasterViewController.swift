//
//  MasterViewController.swift
//  Star Wars Films
//
//  Created by Madi Yost on 3/3/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit

class MasterViewController: UITableViewController {

    var detailViewController: DetailViewController? = nil
//    var objects = [Any]()
    var films = [Film]()
    
    func loadjson(){
        let urlPath = "https://swapi.co/api/films/"
        //use a guard statement to catch the errors at the top
        guard let url = URL(string: urlPath)
            else {
                print("url error")
                return
        }
        let session = URLSession.shared.dataTask(with: url, completionHandler: {(data, response, error) in
            let httpResponse = response as! HTTPURLResponse
            let statusCode = httpResponse.statusCode
            //guard statement to make sure request was successful
            guard statusCode == 200
                else{
                    print("file download error")
                    return
            }
            //download successful
            print("download complete")
            //parse json asynchronously
            DispatchQueue.main.async {self.parsejson(data!)}
        })
        //must call resume session
        session.resume()
    }
    
    func parsejson(_ data: Data){
        do{
            let api = try JSONDecoder().decode(FilmData.self, from: data)
            //print(api)
            for film in api.results {
                films.append(film)
            }
        }
        catch let jsonErr {
            print(jsonErr.localizedDescription)
            return
        }
        //reload the table data after json downloaded
        tableView.reloadData()
    }


    override func viewDidLoad() {
        super.viewDidLoad()
        loadjson()
        // Do any additional setup after loading the view, typically from a nib.
        if let split = splitViewController {
            let controllers = split.viewControllers
            detailViewController = (controllers[controllers.count-1] as! UINavigationController).topViewController as? DetailViewController
        }
    }

    override func viewWillAppear(_ animated: Bool) {
        clearsSelectionOnViewWillAppear = splitViewController!.isCollapsed
        super.viewWillAppear(animated)
    }

    // MARK: - Segues

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showDetail" {
            if let indexPath = tableView.indexPathForSelectedRow {
                let film = films[indexPath.row]
                let controller = (segue.destination as! UINavigationController).topViewController as! DetailViewController
                //set the variables to hold the text for the labels in the detail view
                controller.detailItem = film.episode_id
                controller.directorItem = film.director
                controller.producerItem = film.producer
                controller.crawlItem = film.opening_crawl
                //set the title of the detail view
                controller.title = film.title
                controller.navigationItem.leftBarButtonItem = splitViewController?.displayModeButtonItem
                controller.navigationItem.leftItemsSupplementBackButton = true
            }
        }
    }

    // MARK: - Table View

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return films.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)

        let film = films[indexPath.row]
        cell.textLabel!.text = film.title
        cell.detailTextLabel!.text = film.director
        return cell
    }


}

