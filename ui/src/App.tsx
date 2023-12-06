import React, {lazy, Suspense} from 'react';
import {Route, Routes} from "react-router";

const PaymentPage = lazy(() => import('./pages/PaymentPage'));

const App: React.FC = () => {
    return (
        <Suspense fallback={<div>Loading...</div>}>
            <Routes>
                <Route path="/" element={<PaymentPage/>}/>
            </Routes>
        </Suspense>
    );
};

export default App;
