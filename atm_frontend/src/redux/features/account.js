import { createSlice } from "@reduxjs/toolkit";

export const accountSlice = createSlice({
    name: 'account',
    initialState: {
        value: {
            accHolderName:'',
            accType:'',
            accBal:''
        }
    },
    reducers: {
        accDetails: (state, action) => {
            state.value = action.payload
        }
    }
})

export const { accDetails } = accountSlice.actions;
export default accountSlice.reducer;