//
//  NoteListViewModel.swift
//  iosApp
//
//  Created by Beyza on 21.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

extension NoteListScreen{
    @MainActor class NoteListViewModel: ObservableObject {
        private var noteDataSource:NoteDataSource? = nil
        
        private let searchNotes = SearchNotes()
        private var notes=[Note]()
        @Published private(set) var filteredNotes = [Note]()
        @Published var searchText=""{
            didSet{
                self.filteredNotes = searchNotes.execute(notes: self.notes, query: searchText)
            }
        }
        @Published private(set) var isSearchActive = false
        
        init(noteDataSource:NoteDataSource? = nil){
            self.noteDataSource=noteDataSource
        
        }
        
        func loadNotes()
        {
            noteDataSource?.getAllNotes(completionHandler: {notes,error in
                self.notes = notes ?? []
                self.filteredNotes = self.notes
            })
        }
        
        func deleteNoteById(id:Int64?){
            if id != nil {
                noteDataSource?.deleteNoteById(id: id!, completionHandler: {error in
                    self.loadNotes()})
            }
        }
        func toggleSearchActive()        {
            self.isSearchActive = !self.isSearchActive
            if !self.isSearchActive{
                searchText=""
            }
        }
        func setNoteDataSource(noteDataSource:NoteDataSource){
            self.noteDataSource = noteDataSource
        }
    }
}
