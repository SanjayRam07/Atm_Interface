import {createSlice} from '@reduxjs/toolkit'

export const userSlice = createSlice({
    name: 'user',
    initialState: {
        value: {
            id:'',
            pwd:''
        }
    },
    reducers: {
        signin: (state, action) => {
            state.value = action.payload
        }
    }
})

export const {signin} = userSlice.actions;
export default userSlice.reducer;