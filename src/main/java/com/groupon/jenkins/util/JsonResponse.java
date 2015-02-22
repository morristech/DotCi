/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014, Groupon, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.groupon.jenkins.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONSerializer;
import org.kohsuke.stapler.StaplerResponse;

import java.io.IOException;

public class JsonResponse {
    public static void  render(StaplerResponse rsp, Object output) throws IOException {
        rsp.setContentType("application/json;charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        mapper.setVisibilityChecker(mapper.getSerializationConfig().getDefaultVisibilityChecker().
                withGetterVisibility(JsonAutoDetect.Visibility.PUBLIC_ONLY).
                withSetterVisibility(JsonAutoDetect.Visibility.NONE));
        mapper.writeValue(rsp.getOutputStream(),output);
        rsp.flushBuffer();
    }
}
