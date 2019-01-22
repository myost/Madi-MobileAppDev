//
//  ViewController.swift
//  lab3
//
//  Created by Madi Yost on 9/30/18.
//  Copyright © 2018 Madi Yost. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var itemPrice: UITextField!
    @IBOutlet weak var discountPercent: UITextField!
    @IBOutlet weak var salexTax: UITextField!
    @IBOutlet weak var discountAmount: UILabel!
    @IBOutlet weak var taxAmount: UILabel!
    @IBOutlet weak var totalCost: UILabel!

    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    func updateTotals(){
        //create variables to hold input
        var price:Float
        var discount:Float
        var tax:Float
        //check for valid input for all three text fields
        if itemPrice.text!.isEmpty { //text is an optional string, need to force unwrap to check if it is empty
            price = 0.0
        }else {
            price = Float(itemPrice.text!)! //converts the string to a float
        }
        //create an alert if the item price is zero (item must have a cost to calculate)
        if price == 0 {
            let alert = UIAlertController(title: "Warning", message: "The item price must be greater than 0", preferredStyle: .alert)
            let cancelAction = UIAlertAction(title: "Cancel", style: .cancel, handler: nil)
            alert.addAction(cancelAction)
            let okAction = UIAlertAction(title: "OK", style: .default, handler:
            {action in
                self.itemPrice.text="5"
                self.updateTotals()
            })
            alert.addAction(okAction)
            present(alert, animated: true, completion: nil)
        }
        
        if discountPercent.text!.isEmpty {
            discount = 0.0
        }
        else {
            discount = Float(discountPercent.text!)!/100
        }
        if salexTax.text!.isEmpty {
            tax = 0.0
        }
        else {
            tax = Float(salexTax.text!)!/100
        }
        //do the calculations
        let discountAmt = price*discount
        let itemCost = price-discountAmt
        let taxTotal = itemCost*tax
        let itemTotal = taxTotal+itemCost
        
        //format our results correctly as currency values
        let currencyFormatter = NumberFormatter()
        currencyFormatter.numberStyle=NumberFormatter.Style.currency //set the number style
        discountAmount.text=currencyFormatter.string(from: NSNumber(value: discountAmt)) //returns a formatted string
        taxAmount.text=currencyFormatter.string(from: NSNumber(value: taxTotal))
        totalCost.text=currencyFormatter.string(from: NSNumber(value: itemTotal))
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        updateTotals()
    }
    @IBAction func handleTap(_ sender: UITapGestureRecognizer) {
        if sender.state == .ended {
            itemPrice.resignFirstResponder()
            salexTax.resignFirstResponder()
            discountPercent.resignFirstResponder()
        }
    }
    
    override func viewDidLoad() {
        itemPrice.delegate = self
        salexTax.delegate = self
        discountPercent.delegate = self
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

