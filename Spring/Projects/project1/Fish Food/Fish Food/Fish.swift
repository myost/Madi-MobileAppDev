//
//  Fish.swift
//  Fish Food
//
//  Created by Madi Yost on 3/6/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import Foundation
import RealmSwift

class Fish: Object {
    @objc dynamic var name = ""
    @objc dynamic var species = ""
    @objc dynamic var details = ""
    @objc dynamic var tankSize = 0
    @objc dynamic var image = ""
    @objc dynamic var age = Date(timeIntervalSince1970: 0)
}
