import logo from '../logo.svg';
import './App.css';
import AuthorizationComponent from "./authorization/authorization";
import {BrowserRouter, HashRouter, Route, Router, Routes} from "react-router-dom";
import React from "react";
import MainPageComponent from "./main-page/main-page";
import UserListComponent from "./user-list/user-list";
import RegistrationComponent from "./registration/registration";
import ProfileComponent from "./profile/profile";
import PostUserInfoComponent from "./post-user-info/post-user-info";
import InterestsComponent from "./interests/interests";
import ChangeUserInfoComponent from "./change-user-info/change-user-info";

function App() {
  return (
      <BrowserRouter>
          <div className="app">
              <Routes>
                  <Route exact path="/" element={<MainPageComponent/>} />
                  <Route path="/authorization" element={<AuthorizationComponent/>} />
                  <Route path="/userlist" element={<UserListComponent/>} />
                  <Route path="/registration" element={<RegistrationComponent/>} />
                  <Route path="/profile/:id" element={<ProfileComponent />} />
                  <Route path="/registration/part2" element={<PostUserInfoComponent/>} />
                  <Route path="/interests" element={<InterestsComponent/>} />
                  <Route path="/change" element={<ChangeUserInfoComponent/>} />
              </Routes>
          </div>
      </BrowserRouter>
  );
}

export default App;
