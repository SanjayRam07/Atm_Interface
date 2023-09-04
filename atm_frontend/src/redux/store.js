import { configureStore } from '@reduxjs/toolkit'
import accountSlice from './features/account';
import userSlice from './features/user'

const store = configureStore({
    reducer:{
        user: userSlice,
        account: accountSlice,
    }
});

 export default store;