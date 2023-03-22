package com.tomczyn.linkding.common

import arrow.core.Either

typealias Response<ERR, RES> = Either<RequestError<out ERR>, RES>
