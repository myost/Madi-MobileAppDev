//
//  ShopViewController.swift
//  Fish Food
//
//  Created by Madi Yost on 3/10/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit
import WebKit

class ShopViewController: UIViewController, WKNavigationDelegate {
    let shopUrl = "https://www.petsmart.com/fish/live-fish/"
    @IBOutlet weak var webSpinner: UIActivityIndicatorView!
    @IBOutlet weak var shopWebView: WKWebView!
    
    func loadWebPage(_ urlString: String){
        //the urlString should be a propery formed url
        //creates a URL object
        let myurl = URL(string: urlString)
        //create a URLRequest object
        let request = URLRequest(url: myurl!)
        //load the URLRequest object in our web view
        shopWebView.load(request)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        shopWebView.navigationDelegate = self
        loadWebPage(shopUrl)
        // Do any additional setup after loading the view.
    }
    //WKNavigationDelegate method that is called when a web page begins to load
    func webView(_ webView: WKWebView, didStartProvisionalNavigation navigation: WKNavigation!) {
        webSpinner.startAnimating()
    }
    
    //WKNavigationDelegate method that is called when a web page loads successfully
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        webSpinner.stopAnimating()
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
