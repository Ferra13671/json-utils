package com.ferra13671.jsonutils.model.reader;

import com.ferra13671.jsonutils.model.JElement;

import java.io.Reader;

public interface JReader {
    
    JElement read(Reader reader);
}
