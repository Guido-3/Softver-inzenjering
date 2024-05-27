import { Link, useNavigate } from "react-router-dom";

export default function CreateSight() {

    const navigate=useNavigate()

    async function handleSubmit(event) {
        event.preventDefault()

        const formData = new FormData(event.target)
        const sight = Object.fromEntries(formData.entries())

        if(!sight.name || !sight.description || !sight.image.name || !sight.admin){
            alert("Popunite sva polja!")
            return
        }
        try {
            const response = await fetch("http://localhost:8080/citypass-api/znamenitost", {
                method: "POST",
                body: formData
            })

            const data = await response.json()

            if(response.ok) {
                alert("Uspijesno kreirana znamenitost")
                navigate("/admin/sights")
            }else if(response.status===400){
                alert("Validation error")
            }else {
                alert("Neuspijesno kreiranje znamenitosti")
            }
        } catch (error) {
            alert("Unable to connect to the server")
        }
    }

    return (
        <div className="container my-4">
            <div className="row">
                <div className="col-md-8 mx-auto rounded border p-4">
                    <h2 className="text-center mb-5">Dodajte znamenitost</h2>

                    <form onSubmit={handleSubmit}>
                        <div className="row mb-3">
                            <label className="col-sm-4 col-form-label">Ime</label>
                            <div className="col-sm-8"> 
                                <input className="form-control" name="name"/>
                                <span className="text-danger"></span>
                            </div>
                        </div>

                        <div className="row mb-3">
                            <label className="col-sm-4 col-form-label">Opis</label>
                            <div className="col-sm-8">
                                <input className="form-control" name="description"/>
                                <span className="text-danger"></span>
                            </div>
                        </div>

                        <div className="row mb-3">
                            <label className="col-sm-4 col-form-label">Slika</label>
                            <div className="col-sm-8">
                                <input className="form-control" type="file" name="image"/>
                                <span className="text-danger"></span>
                            </div>
                        </div>

                        <div className="row mb-3">
                            <label className="col-sm-4 col-form-label">Admin</label>
                            <div className="col-sm-8">
                                <select className="form-select" name="admin">
                                    <option value="other">Other</option>
                                    <option value="Elica">Elica</option>
                                    <option value="Gvido">Gvido</option>
                                    <option value="Krizo">Krizo</option>
                                    <option value="Milica">Milica</option>
                                    <option value="Sara">Sara</option>
                                </select>
                            </div>
                        </div>

                        <div className="row">
                            <div className="offset-sm-4 col-sm-4 d-grid">
                                <button type="submit" className="btn btn-primary">Submit</button>
                            </div>
                            <div className="col-sm-4 d-grid">
                                <Link className="btn btn-secondary" to="/admin/sights" role="button">Cancel</Link>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    )
}