//
//  FirstViewController.swift
//  lab1_tabs
//
//  Created by Madi Yost on 2/1/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    @IBOutlet weak var destPicker: UIPickerView!
    @IBOutlet weak var choiceLabel: UILabel!
    
    var continentComponent = 0
    var citiesComponent = 1
    
    var worldDests = WorldDestinationsController() //for dealing with the data
    var continents = [String]() //list of continents
    var cities = [String]() //list of cities in the selected continent
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        worldDests.loadData()
        continents = worldDests.getContinents()
        //get the list of cities for the first continent bc picker will automatically go to that continent first
        cities = worldDests.getCities(index: 0)
        choiceLabel.text = "You want to travel to \(cities[0]) in \(continents[0])"
        
    }
    
    //required methods for the UIPickerDataSource protocol
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        //hard coded number of components because it will not change
        return 2
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if component == continentComponent{
            return continents.count
        }else{
            return cities.count
        }
    }
    
    //picker Delegate methods
    //return the title for a row
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        //checks which component was picked and then renders the correct title for that component
        if component == continentComponent {
            return continents[row]
        }else{
            return cities[row]
        }
    }
    
    //function called when a row is selected
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        //check which component was picked
        if component == continentComponent {
            cities = worldDests.getCities(index: row) //get the cities in the selected continent
            destPicker.reloadComponent(citiesComponent) //reload the cities component to reflect the changes
            destPicker.selectRow(0, inComponent: citiesComponent, animated: true) //set the album component back to zero - always use animated true
        }
        let continentRow = pickerView.selectedRow(inComponent: continentComponent) //get selected continent row
        let citiesRow = pickerView.selectedRow(inComponent: citiesComponent) //get selected city row
        choiceLabel.text = "You want to travel to \(cities[citiesRow]) in \(continents[continentRow])"
    }
}

