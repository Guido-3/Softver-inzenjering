

//http://localhost:8080/citypass-api/znamenitost

import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function SightsList () {

    const [sights, setSights] = useState([])

    function getSights() {
        fetch("http://localhost:8080/citypass-api/znamenitost")
        .then(response => {
            if(response.ok) {
                return response.json()
            }

            throw new Error()
        })
        .then(data => {
            setSights(data)
        })
        .catch(error => {
            alert("Unable to get the data")
        })
    }

    useEffect(getSights, [])

    return (
        <div className="container my-4">
            <h2 className="text-center mb-4">Znamenitosti</h2>
        

            <div className="row">
                <div className="col">
                    <Link className="btn btn-primary me-1 mb-3" to="/admin/sights/create" role="button">Dodaj Znamenitost</Link>
                    <button type="button" className="btn btn-outline-primary mb-3"
                    onClick={getSights}>Refresh</button>
                </div>
                <div className="col">

                </div>
            </div>

            <table className="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Ime</th>
                        <th>Opis</th>
                        <th>Slika</th>
                        <th>Admin</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        sights.map((sight, index) => {
                            return (
                                <tr key={index}>
                                    <td>{sight.id}</td>
                                    <td>{sight.ime}</td>
                                    <td>{sight.opis}</td>
                                    <td>{sight.slika}</td>
                                    <td>{sight.admin}</td>
                                    <td style={{width: "10px", whiteSpace: "nowrap"}}>
                                        <Link className="btn btn-primary btn-sm me-1"
                                            to={"/admin/sights/edit/" + sight.id}>Edit</Link>
                                        <button type="button" className="btn btn-danger btn-sm">Delete</button>
                                    </td>
                                </tr>
                            )
                        })
                    }
                </tbody>
            </table>
        </div>
    )
}