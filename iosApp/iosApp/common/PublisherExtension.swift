//
//  PublisherExtension.swift
//  iosApp
//
//  Created by Maciej Tomczyński on 02/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Combine

extension Publisher where Failure == Error {
    func ignoreErrors() -> AnyPublisher<Output, Never> {
        self.catch { _ in Empty<Output, Never>() }
            .eraseToAnyPublisher()
    }
}
