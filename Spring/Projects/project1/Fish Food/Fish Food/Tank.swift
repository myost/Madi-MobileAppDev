//
//  Tank.swift
//  Fish Food
//
//  Created by Madi Yost on 3/7/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import Foundation
import RealmSwift

class Tank: Object{
    @objc dynamic var lastFed = Date(timeIntervalSinceNow: 0)
    let fishInTank = List<Fish>()
}
