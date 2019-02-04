//
//  frameworkViewController.swift
//  lab1_tabs
//
//  Created by Madi Yost on 2/1/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import UIKit
import AVFoundation

class frameworkViewController: UIViewController, AVAudioPlayerDelegate {
    var audioPlayer: AVAudioPlayer?
    let africaFile = "africa"
    let asiaFile = "aisa"
    let europeFile = "europe"
    let ausFile = "aus"
    
    @IBOutlet weak var afPlayButton: UIButton!
    @IBOutlet weak var afStopButton: UIButton!
    @IBOutlet weak var asPlaybutton: UIButton!
    @IBOutlet weak var asStopButton: UIButton!
    @IBOutlet weak var eurPlayButton: UIButton!
    @IBOutlet weak var eurStopButton: UIButton!
    @IBOutlet weak var ausPlayButton: UIButton!
    @IBOutlet weak var ausStopButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //disable all stop buttons
        afStopButton.isEnabled = false
        asStopButton.isEnabled = false
        ausStopButton.isEnabled = false
        eurStopButton.isEnabled = false
        //the shared audio session instance
        let audioSession = AVAudioSession.sharedInstance()
        do {
            //sets the category for recording and playback of audio
            try audioSession.setCategory(AVAudioSession.Category.playback, mode: .default, options: [])
        } catch {
            print("audio session error: \(error.localizedDescription)")
        }
        // Do any additional setup after loading the view.
    }
    
    @IBAction func playAfAudio(_ sender: Any) {
        asPlaybutton.isEnabled = false
        ausPlayButton.isEnabled = false
        eurPlayButton.isEnabled = false
        asStopButton.isEnabled = false
        ausStopButton.isEnabled = false
        eurStopButton.isEnabled = false
        playAudio(fileName: africaFile, playButton: afPlayButton, stopButton: afStopButton)
    }
    
    @IBAction func playAsAudio(_ sender: Any) {
        afPlayButton.isEnabled = false
        ausPlayButton.isEnabled = false
        eurPlayButton.isEnabled = false
        afStopButton.isEnabled = false
        ausStopButton.isEnabled = false
        eurStopButton.isEnabled = false
        playAudio(fileName: asiaFile, playButton: asPlaybutton, stopButton: asStopButton)
    }
    
    @IBAction func playEurAudio(_ sender: Any) {
        afPlayButton.isEnabled = false
        asPlaybutton.isEnabled = false
        ausPlayButton.isEnabled = false
        afStopButton.isEnabled = false
        asStopButton.isEnabled = false
        ausStopButton.isEnabled = false
        playAudio(fileName: europeFile, playButton: eurPlayButton, stopButton: eurStopButton)
    }
    
    @IBAction func playAusAudio(_ sender: Any) {
        afPlayButton.isEnabled = false
        asPlaybutton.isEnabled = false
        eurPlayButton.isEnabled = false
        afStopButton.isEnabled = false
        asStopButton.isEnabled = false
        eurStopButton.isEnabled = false
        playAudio(fileName: ausFile, playButton: ausPlayButton, stopButton: ausStopButton)
    }
    
    func playAudio(fileName : String, playButton : UIButton, stopButton : UIButton){
        playButton.isEnabled = false
        stopButton.isEnabled = true
        let fileURL = getPath(fileName: fileName)
        do {
            try audioPlayer = AVAudioPlayer(contentsOf: fileURL!)
            audioPlayer!.delegate = self
            audioPlayer!.prepareToPlay()
            audioPlayer!.play()
        } catch let error {
            print("audioPlayer error: \(error.localizedDescription)")
        }
        
    }
    @IBAction func afStopAudio(_ sender: Any) {
        stopAudio()
    }
    @IBAction func asStopAudio(_ sender: Any) {
        stopAudio()
    }
    @IBAction func eurStopAudio(_ sender: Any) {
        stopAudio()
    }
    @IBAction func ausStopAudio(_ sender: Any) {
        stopAudio()
    }
    
    
    func stopAudio(){
        audioPlayer?.stop()
        afStopButton.isEnabled = false
        asStopButton.isEnabled = false
        ausStopButton.isEnabled = false
        eurStopButton.isEnabled = false
        afPlayButton.isEnabled = true
        asPlaybutton.isEnabled = true
        ausPlayButton.isEnabled = true
        eurPlayButton.isEnabled = true
    }
    
    func getPath(fileName : String)->URL?{
        //get the path for the audio file
        let audioFileURL = Bundle.main.url(forResource: fileName, withExtension: "mp3")
        return audioFileURL
    }
    
    
    //AVAudioPlayerDelegate method
    //Called when a recording is stopped or has finished due to reaching its time limit
    func audioPlayerDidFinishPlaying(_ player: AVAudioPlayer, successfully
        flag: Bool) {
        afStopButton.isEnabled = false
        asStopButton.isEnabled = false
        ausStopButton.isEnabled = false
        eurStopButton.isEnabled = false
        afPlayButton.isEnabled = true
        asPlaybutton.isEnabled = true
        ausPlayButton.isEnabled = true
        eurPlayButton.isEnabled = true
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
