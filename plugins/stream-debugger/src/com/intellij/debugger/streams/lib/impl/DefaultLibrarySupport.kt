/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.debugger.streams.lib.impl

import com.intellij.debugger.streams.lib.*
import com.intellij.debugger.streams.resolve.EmptyResolver
import com.intellij.debugger.streams.resolve.ValuesOrderResolver
import com.intellij.debugger.streams.trace.CallTraceInterpreter
import com.intellij.debugger.streams.trace.IntermediateCallHandler
import com.intellij.debugger.streams.trace.TerminatorCallHandler
import com.intellij.debugger.streams.trace.dsl.Dsl
import com.intellij.debugger.streams.trace.impl.handler.unified.PeekTraceHandler
import com.intellij.debugger.streams.trace.impl.handler.unified.TerminatorTraceHandler
import com.intellij.debugger.streams.trace.impl.interpret.SimplePeekCallTraceInterpreter
import com.intellij.debugger.streams.wrapper.IntermediateStreamCall
import com.intellij.debugger.streams.wrapper.TerminatorStreamCall

class DefaultLibrarySupport : LibrarySupport {
  override fun createHandlerFactory(dsl: Dsl): HandlerFactory = object : HandlerFactory {
    override fun getForIntermediate(number: Int, call: IntermediateStreamCall): IntermediateCallHandler {
      return PeekTraceHandler(number, call.name, call.typeBefore, call.typeAfter, dsl)
    }

    override fun getForTermination(call: TerminatorStreamCall, resultExpression: String): TerminatorCallHandler {
      return TerminatorTraceHandler(call, dsl)
    }
  }

  override val interpreterFactory: InterpreterFactory = object : InterpreterFactory {
    override fun getInterpreter(callName: String): CallTraceInterpreter {
      return SimplePeekCallTraceInterpreter()
    }
  }

  override val resolverFactory: ResolverFactory = object : ResolverFactory {
    override fun getResolver(callName: String): ValuesOrderResolver {
      return EmptyResolver()
    }
  }
}