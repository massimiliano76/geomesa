/***********************************************************************
 * Copyright (c) 2013-2020 Commonwealth Computer Research, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution and is available at
 * http://www.opensource.org/licenses/apache2.0.php.
 ***********************************************************************/

package org.locationtech.geomesa.hbase.rpc.coprocessor

import java.util.concurrent.{ConcurrentLinkedQueue, LinkedBlockingQueue}

import com.google.protobuf.ByteString
import org.apache.hadoop.hbase.client.coprocessor.Batch.Callback

class GeoMesaHBaseCallBack extends Callback[java.util.List[ByteString]] {

  val result = new LinkedBlockingQueue[ByteString]()

  override def update(region: Array[Byte], row: Array[Byte], result: java.util.List[ByteString]): Unit =
    if (result != null) { this.result.addAll(result) }
}
