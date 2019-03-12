//
//  FirstViewController.swift
//  Fish Food
//
//  Created by Madi Yost on 3/6/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit
import UICircularProgressRing
import UserNotifications

class FirstViewController: UIViewController {
    var timerRemainingTime = TimeInterval()
    var timerElapsedTime = TimeInterval()
    var timerTime = Double()
    var defaultDate = Date(timeIntervalSince1970: 0)
    var lastFed = Date()
    var timer = Timer()
    var timerDataController = TimerDataModelController()

    @IBOutlet weak var timeRemainingLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //timeRemainingLabel.text = "Time values: \(elapsedTime) \(time)"
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func viewDidAppear(_ animated: Bool) {
        timerDataController.loadData()
        timerTime = timerDataController.getDefaultTime()
        //timerTime = 120
        //print(timerTime)
        lastFed = timerDataController.getLastFed()
        //print(lastFed)
        //print(defaultDate)
        
        if defaultDate == lastFed {
            //print("in equal")
            lastFed = Date(timeIntervalSinceNow: 0)
            timerDataController.setLastFed(lastFedDate: lastFed)
            timerRemainingTime = timerTime
            scheduleNotification()
            runTimer()
        } else {
            timerElapsedTime = Date(timeIntervalSinceNow: 0).timeIntervalSince(lastFed)
            print(timerElapsedTime)
            if Double(timerElapsedTime) >= timerTime {
                //print("timer ran out")
                //the timer would have run out since the last time the user logged in, present an alert
                timer.invalidate()
                timeRemainingLabel.text = "00:00:00"
                presentAlert()
            } else{
                print("in else")
                timerRemainingTime = timerTime - timerElapsedTime
                runTimer()
            }
        }
    }
    @IBAction func tappedFeedButton(_ sender: Any) {
        timer.invalidate()
        timeRemainingLabel.text = "00:00:00"
        feedFish()
    }
    
    //timer function modified from timer tutorial on Medium https://medium.com/ios-os-x-development/build-an-stopwatch-with-swift-3-0-c7040818a10f
    func runTimer(){
        //print("in timer")
        timer = Timer.scheduledTimer(timeInterval: 1, target: self, selector: #selector(FirstViewController.updateTimer), userInfo: nil, repeats: true)
    }
    
    func feedFish(){
        lastFed = Date(timeIntervalSinceNow: 0)
        timerDataController.setLastFed(lastFedDate: lastFed)
        timerRemainingTime = timerTime
        let notificationCenter = UNUserNotificationCenter.current()
        notificationCenter.removePendingNotificationRequests(withIdentifiers: ["feedFishNotification"])
        scheduleNotification()
        runTimer()
    }
    
    func presentAlert(){
        let alert = UIAlertController(title: "Feed Fish", message: "It's time to feed your fish", preferredStyle: .alert)
        let cancelAction = UIAlertAction(title: "Cancel", style:UIAlertAction.Style.cancel, handler: nil)
        //add the alert action to the alert object
        alert.addAction(cancelAction)
        let okAction = UIAlertAction(title: "Feed", style: .default, handler: { action in
            self.feedFish()
        })
        alert.addAction(okAction)
        present(alert, animated: true, completion: nil)
    }
    
    func scheduleNotification(){
        let notificationCenter = UNUserNotificationCenter.current()
        notificationCenter.getNotificationSettings { (settings) in
            print("inside notification")
            // Do not schedule notifications if not authorized.
            guard settings.authorizationStatus == .authorized else {return}
            //create the notification
            let content = UNMutableNotificationContent()
            content.title = "Feed Fish"
            content.body = "It's time to feed your fish!"
            let trigger = UNTimeIntervalNotificationTrigger(timeInterval: self.timerRemainingTime, repeats: false)
            let notificationIdentifier = "feedFishNotification"
            let request = UNNotificationRequest(identifier: notificationIdentifier,
                                                content: content, trigger: trigger)
            
            if settings.alertSetting == .enabled {
                // Schedule an alert-only notification.
                notificationCenter.add(request) { (error) in
                    if error != nil {
                        // Handle any errors.
                        print(error)
                    }
                }
            }
            else {
                // Schedule a notification with a badge and sound.
                content.badge = 1
                notificationCenter.add(request) { (error) in
                    if error != nil {
                        // Handle any errors.
                        print(error)
                    }
                }
            }
        }
    }
    
    @objc func updateTimer(){
        //print("in update")
        if timerRemainingTime < 1 {
            //stop the timer from repeating
            timer.invalidate()
            timeRemainingLabel.text = "00:00:00"
            //send alert to indicate the time is up
            presentAlert()
            
        } else {
            timerRemainingTime -= 1
            //print(timerRemainingTime)
            timeRemainingLabel.text = timerString(time: timerRemainingTime)
            
        }
    }
    
    func timerString(time: TimeInterval) -> String {
        let hours = Int(timerRemainingTime) / 3600
        let minutes = Int(timerRemainingTime) / 60 % 60
        let seconds = Int(timerRemainingTime) % 60
        let numberFormatter = NumberFormatter()
        numberFormatter.minimumIntegerDigits = 2
        let hourString = numberFormatter.string(from: hours as NSNumber)
        let minString = numberFormatter.string(from: minutes as NSNumber)
        let secString = numberFormatter.string(from: seconds as NSNumber)
        return hourString!+":"+minString!+":"+secString!
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        //save the time left to the plist
        timer.invalidate()
        timerDataController.writeData()
    }

}

