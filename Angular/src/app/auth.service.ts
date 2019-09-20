import { resolve, reject } from "q";
​
export class AuthService {
    loggedIn = true;
​
    isAuthenticated(){
        const promise = new Promise(
            (resolve, reject) => {
                setTimeout(() => {
                    resolve(this.loggedIn);
                }, 800);
            }
        );
        return promise;
    }
​
    loginFlag() {
        this.loggedIn = true;
    }
​
    logoutFlag() {
        this.loggedIn = false;
    }
}